import { Component, EventEmitter, Input, Output } from '@angular/core';
import {Log} from '../common/Log'

@Component({
    selector: 'worklog-editor',
    templateUrl: 'app/worklogEditor/worklogEditor.html',
})
export class WorklogEditor {
    @Input() logToEdit : Log;
    @Output() onChange = new EventEmitter<Log>();
    
    public done() {
        this.onChange.emit(this.logToEdit);
    }
}