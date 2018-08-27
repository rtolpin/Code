import { Component, OnInit, Input, ElementRef } from '@angular/core';
import { TagCount } from 'altru-core';
import * as d3 from 'd3';

@Component({
  selector: 'altru-bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrls: ['./bar-chart.component.scss']
})
export class BarChartComponent implements OnInit {

  @Input()
  dataSet: TagCount;

  @Input()
  objectKeys: string[];

  constructor(private element: ElementRef) { }

  ngOnInit() {
    let opacityCounter: number = 0;
    const margin: Object = { top: 20, right: 20, bottom: 20, left: 20 };
    const width: number = 300 - margin['left'] - margin['right'];
    const height: number = 200 - margin['top'] - margin['bottom'];
    const element: ElementRef = this.element;
    const dataSet: TagCount = this.dataSet;
    const objectKeys: string[] = this.objectKeys;

    // Instantiate the d3 chart
    const chart: any = d3.select(element.nativeElement)
      .append('svg')
      .attr('class', 'bar-chart')
      .attr('width', width + margin['left'] + margin['right'])
      .attr('height', height + margin['top'] + margin['bottom'])
      .append('g')
      .attr('class', 'chart-data')
      .attr('transform', 'translate(-20,24)');

    // Define x and y scalers
    const x: any = d3.scaleBand().domain(objectKeys).rangeRound([0, width]).padding(.05);
    const y: any = d3.scaleLinear().domain([0, this.dataSet[objectKeys[0]]]).range([height, 0]);

    // Define x and y axis
    const xAxis: any = d3.axisBottom(x).tickSize(0).tickPadding(0);
    const yAxis: any = d3.axisLeft(y).tickSize(0).tickPadding(20).ticks(4);

    // Add the x and y axis to the chart
    const addYAxis: any = chart.append('g').attr('class', 'y-axis').attr('transform', 'translate(30,0)').call(yAxis);
    const addXAxis: any = chart.append('g').attr('class', 'x-axis').call(xAxis);

    // Space out y-axis ticks
    addYAxis
      .selectAll('.tick')
      .attr('transform', function(): any {
        return d3.select(this).attr('transform') + 'translate(30,-12)';
      });

    // Add horizontal tick marks
    chart.selectAll('line.horizontalGrid')
      .data(y.ticks(4).slice(1))
      .enter()
      .append('line')
      .attr('class', 'horizontalGrid')
      .attr('x1', margin['right'])
      .attr('x2', width + margin['right'] + margin['left'])
      .attr('y1', y)
      .attr('y2', y);

    // Draw vertical bars, add mouse-over logic
    chart.selectAll('rect')
      .data(objectKeys)
      .enter()
      .append('rect')
      .attr('class', 'bar')
      .attr('height', (d => Math.abs(y(dataSet[d]) - y(0)) + 10))
      .attr('width', '11px')
      .attr('x', x)
      .attr('y', (d => y(dataSet[d])))
      .attr('rx', 5.5)
      .attr('ry', 5.5)
      .attr('transform', 'translate(50,-2)')
      .style('opacity', () => 1 - (opacityCounter++ / (1.5 * objectKeys.length)))
      .on('mouseover', function(d: any): void {
        d3.select(this).style('fill', '#0000b6');
        d3.select(element.nativeElement)
          .select('svg')
          .append('foreignObject')
          .attr('class', 'tooltip-container')
          .attr('width', '100%')
          .attr('height', '100%')
          .append('xhtml:div')
          .attr('class', 'tooltip')
          .style('top', d3.max([(y(dataSet[d]) - 5), 0]) + 'px')
          .style('left', x(d) > width - (width / 5) ? x(d) - 20 + 'px' : x(d) + 'px')
          .append('span')
          .attr('class', 'tooltip-text')
          .text(`${d}: ${dataSet[d]}`);
      })
      .on('mouseout', function(): void {
        d3.select(element.nativeElement).select('foreignObject').remove();
        d3.select(this).style('fill', '#48D1CC');
      });

    // Draw X-Axis
    chart.append('line')
      .attr('x1', 0)
      .attr('x2', width + margin['right'] + 22)
      .attr('y1', height)
      .attr('y2', height)
      .attr('class', 'x-axis-draw-line');

    // Draw white box to hide rounded bottom of bars
    chart.append('line')
      .attr('x1', 0)
      .attr('x2', width + margin['right'] + 22)
      .attr('y1', height + 5)
      .attr('y2', height + 5)
      .attr('class', 'white-box');
  }

}
