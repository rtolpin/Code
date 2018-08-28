import { Component, OnInit } from '@angular/core';
import { ClientReach, AnalyticsService } from 'altru-core';
import { ParsePipe, DateFormatPipe } from 'ngx-moment';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'altru-video-views-chart',
  templateUrl: './video-views-chart.component.html',
  styleUrls: ['./video-views-chart.component.scss'],
  providers: [ParsePipe, DateFormatPipe]
})
export class VideoViewsChartComponent implements OnInit {

  public clientReach: ClientReach;
  public FORMAT_DAY: string = 'M/D/YY';
  public FORMAT_WEEK: string = '[week] W \'YY';
  public FORMAT_MONTH: string = 'MMM YYYY';
  public currentFormat: string;
  public loading: boolean = true;
  public response: object[];

  constructor(
    private parsePipe: ParsePipe,
    private dateFormatPipe: DateFormatPipe,
    private analyticsService: AnalyticsService
  ) { }

  public ngOnInit(): void {
    this.analyticsService.getClientReach().subscribe(res => {
      this.clientReach = res.data;
      this.setGroupBy(this.FORMAT_MONTH);
      this.loading = false;
    });
  }

  public refreshData(): void {
    this.loading = true;
    this.analyticsService.getClientReach(true).pipe(
      tap(() => this.loading = false)
    ).subscribe(res => {
      this.clientReach = res.data;
      this.setGroupBy(this.currentFormat, true);
      this.loading = false;
    });
  }

  public setGroupBy(groupByFormat: string, refresh: boolean = false): void {
    if (refresh || this.currentFormat !== groupByFormat) {
      this.currentFormat = groupByFormat;
      const client_reach_data: object = Object.values(this.clientReach['views_by_day']['data']['values'])[0];
      const dataObject: object = {};
      const dataArray: object[] = [];

      for (const key of Object.keys(client_reach_data)) {
        const value: string = client_reach_data[key];
        const dateIndex: string = this.dateFormatPipe.transform(this.parsePipe.transform(key, 'YYYY-MM-DD'), groupByFormat).toUpperCase();
        dataObject[dateIndex] = dataObject[dateIndex] == null ? value : dataObject[dateIndex] + value;
      }

      // Convert data to an array of {label: value} objects as the directive expects
      Object.keys(dataObject).map(key => {
        const newObj: {} = {};
        newObj[key] = dataObject[key];
        dataArray.push(newObj);
      });

      // Sort the {label: value} objects so the graph shows them in order
      this.response = dataArray.sort((dataPoint1, dataPoint2) =>
        this.parsePipe.transform(Object.keys(dataPoint1)[0], groupByFormat)
        < this.parsePipe.transform(Object.keys(dataPoint2)[0], groupByFormat)
        ? -1 : 1
      );

    }
  }
}
