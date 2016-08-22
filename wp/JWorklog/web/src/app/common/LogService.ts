import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable }     from 'rxjs/Observable';
import { Log } from './Log';

@Injectable()
export class LogService {

    private URL: string = "http://localhost:4567";

    constructor(private http: Http) { }

    public getLogs(): Observable<Log[]> {
        return this.http.get(this.URL + "/logs")
            .map(this.extractData)
            .catch(this.handleError);
    }

    public save(log: Log): Observable<Object> {
        console.info("Update log with id " + log.id);
        console.info("Update log: " + JSON.stringify(log));

        let body = JSON.stringify(log);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        return this.http.post(this.URL + "/log", body, options)
            .catch(this.handleError);
    }

    public delete(logId: number): Observable<Object> {
        return this.http.delete(this.URL + "/log/" + logId)
                        .catch(this.handleError);
    }

    private extractData(res: Response) {
        let body = res.json();
        if (body instanceof Array) {
            let parsedLogs: Array<Log> = [];
            for (var item of body) {
                let log: Log = new Log(item["id"], item["date"], item["logText"]);
                parsedLogs.push(log);
            }
            return parsedLogs;
        } else {
            return [];
        }
    }


    private handleError(error: any) {
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }

}