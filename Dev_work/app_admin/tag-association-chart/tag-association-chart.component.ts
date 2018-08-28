import { Component, Input, OnInit } from '@angular/core';
import { TagCount } from 'altru-core';

export type TagAssociationChartViewType = 'graph' | 'table';

@Component({
  selector: 'altru-tag-association-chart',
  templateUrl: './tag-association-chart.component.html',
  styleUrls: ['./tag-association-chart.component.scss']
})
export class TagAssociationChartComponent implements OnInit {

  @Input()
  public dataSet: TagCount;

  @Input()
  public title: string;

  @Input()
  public viewType: TagAssociationChartViewType = 'graph';

  public objectKeys: string[];

  public ngOnInit(): void {
    this.objectKeys = Object.keys(this.dataSet)
      .filter(key => this.dataSet[key] > 0)
      .sort((a, b) => this.dataSet[a] < this.dataSet[b] ? 1 : -1);
  }

  public noneTagHaveAnswers(): boolean {
    for (const label of this.objectKeys) {
      if (this.dataSet[label] !== 0) {
        return false;
      }
    }
    return true;
  }
}
