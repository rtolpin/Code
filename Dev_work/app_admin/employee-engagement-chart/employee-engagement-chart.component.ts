import { Component, Input, EventEmitter, Output, OnInit } from '@angular/core';
import { EmployeeEngagement } from 'altru-core';
import { NormalizePipe } from '../../pipes/normalize/normalize.pipe';

@Component({
  selector: 'altru-employee-engagement-chart',
  templateUrl: './employee-engagement-chart.component.html',
  styleUrls: ['./employee-engagement-chart.component.scss'],
  providers: [NormalizePipe]
})

export class EmployeeEngagementChartComponent implements OnInit {

  @Input()
  public employeeEngagement: EmployeeEngagement;

  @Output()
  public onRefreshButtonClick: EventEmitter<MouseEvent> = new EventEmitter<MouseEvent>();

  public results: {name: string, value: number}[] = [];

  public employeeEngagementData: object[] = [];

  constructor(public normalizePipe: NormalizePipe) { }

  public ngOnInit(): void {
    this.getEmployeeValues();
  }

  public onRefreshClick($event: MouseEvent): void {
    this.onRefreshButtonClick.emit($event);
  }

  public getEmployeeValues(): void {
    for (const e of Object.keys(this.employeeEngagement || {})) {
      const newObj: {} = {};
      newObj[this.normalizePipe.transform(e)] = this.employeeEngagement[e];
      this.employeeEngagementData.push(newObj);
    }

    this.employeeEngagementData = this.employeeEngagementData.sort(
      (dataPoint1, dataPoint2) => Object.values(dataPoint1)[0] > Object.values(dataPoint2)[0] ? 0 : 1);
  }
}
