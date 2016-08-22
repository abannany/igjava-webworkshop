import * as moment from 'moment';

export class Log{

    id: number;
    date: number;
    logText: string;

    constructor(id: number, date: number, text: string) {
        this.id = id;
        this.date = date;
        this.logText = text;
    }

    get stringDate(): string {
        console.log(moment.unix(this.date / 1000).format("DD-MM-YYYY HH:mm:ss"))
        return moment.unix(this.date / 1000).format("DD-MM-YYYY HH:mm:ss");
    }

    get textPart() {
        return this.logText.substring(0, 10) + "....";
    }

}