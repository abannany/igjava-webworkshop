import { Component } from '@angular/core';
import * as moment from 'moment';
import { WorklogViewer } from './worklogViewer/worklogViewer';
import { WorklogEditor } from './worklogEditor/worklogEditor';
import { Log } from './common/Log'
import { LogService } from './common/LogService'
import './rxjs-operators';


@Component({
    selector: 'my-app',
    templateUrl: 'app/app.html',
    directives: [WorklogViewer, WorklogEditor],
    providers: [LogService]
})
export class AppComponent {
    logToEdit: Log;
    editing:boolean;
    
    constructor(private logService: LogService) {
        this.editing = false;
    }
    
    editLog(logToEdit: Log) {
        this.logToEdit = logToEdit;
        this.editing = true;
    }
    
    doneEditing(newLog: Log) {
        newLog.date = moment.now();
        this.logService.save(newLog)
                .subscribe(res => {this.editing = false});
    }
}