import { Component, EventEmitter, Output, OnInit } from '@angular/core';
import {Log} from '../common/Log'
import {LogService} from '../common/LogService'
import * as moment from 'moment';
import { FaComponent } from 'angular2-fontawesome/components';

@Component({
    selector: 'worklog-viewer',
    directives: [FaComponent],
    templateUrl: 'app/worklogViewer/worklogViewer.html',
    providers: [LogService]
})
export class WorklogViewer implements OnInit {

    @Output() edit = new EventEmitter<Log>();

    private _logs: Array<Log> = [];
    private _status: string = "";

    constructor(private logService: LogService) {}

    ngOnInit() { 
        this.getLogs(); 
    }

    getLogs() {
        this.logService.getLogs().subscribe(
            (logs: Log[]) => this._logs = logs,
            (error: any) => this._status = error);
    }
    
    get logs(): Array<Log> {
        if (this._logs.length > 0) {
            console.log("There are " + this._logs.length + " logs")
        }
        return this._logs;
    }
    
    addLog() {
        this.edit.emit(new Log(-1, moment.now(), ""));
    }
    
    editLog(logToEdit: Log) {
        this.edit.emit(logToEdit);
    }
    
    deleteLog(logId:number) {
        this.logService.delete(logId)
                     .subscribe(res => {this.handleDelete(logId)});
    }
    
    handleDelete(logId:number) {
        for (var index = 0; index < this._logs.length; index++) {
            if (logId == this._logs[index].id) {
                this._logs.splice(index, 1);
            }
        }
    }
    
}