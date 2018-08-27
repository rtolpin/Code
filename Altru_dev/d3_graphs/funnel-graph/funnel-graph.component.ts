import { Component, OnInit, ElementRef, Input } from '@angular/core';
import * as d3 from 'd3';

@Component({
  selector: 'altru-funnel-graph',
  templateUrl: './funnel-graph.component.html',
  styleUrls: ['./funnel-graph.component.scss']
})
export class FunnelGraphComponent implements OnInit {

  @Input()
  data: any;

  constructor(private element: ElementRef){}

  ngOnInit(): void {
    const element: ElementRef = this.element;
    const margin: object = {top: 20, right: 20, bottom: 90, left: 20};
    const width: number = 550 - margin['left'] - margin['right'];
    const height: number = 500 - margin['top'] - margin['bottom'];
    const objectKeys: string[] = this.data.map((d)=> Object.keys(d)[0]);
    const objectValues: number[] = this.data.map((d)=> d[Object.keys(d)[0]]);

    const GRAPH_OFFSET: number = 80;
    const BAR_WIDTH: number = 90;
    let barCorners: object[] = [];
    let polygons: object[] = [];

    let removeTooltip = function(d,i) {
        d3.select(element.nativeElement).select('.tooltip').remove();
        d3.select(element.nativeElement).select(`.bar-${i}`).style('fill', '#98AFC7');
    }

    let addTooltip = function(d,i) {
        d3.select(element.nativeElement).selectAll('.tooltip').remove();
        d3.select(element.nativeElement).select(`.bar-${i}`).style('fill', '#00254b');
        d3.select(element.nativeElement)
          .select('svg')
          .append('foreignObject')
          .attr('class', 'tooltip-container')
          .attr('width', '100%')
          .attr('height', '100%')
          .append('xhtml:div')
          .attr('class','tooltip')
          .style('top', height * (objectValues[0] - objectValues[i]) / (2 * objectValues[0])+'px')
          .style('left', (x(d) + GRAPH_OFFSET) + 'px')
          .append('span')
          .attr('class', 'tooltip-text')
          .text(`${objectValues[i]}`);
      }

    // Instantiate the d3 chart
    const chart: any = d3.select(element.nativeElement)
      .append('svg')
      .attr('class','funnel-graph')
      .attr('width', width + margin['left'] + margin['right'])
      .attr('height', height + margin['top'] + margin['bottom'])
      .append('g')
      .attr('class', 'chart-data')
      .attr('transform', 'translate(-20,24)');

    // Define x and y scalers
    const x: any = d3.scaleBand().domain(objectKeys).rangeRound([0, width]).padding(.05);
    const y: any = d3.scaleLinear().domain([0, 100]).range([height, 0]);

    // Define x and y axis
    const xAxis: any = d3.axisBottom(x).tickSize(0).tickPadding(0);
    const yAxis: any = d3.axisLeft(y).tickSize(0).tickPadding(20).ticks(6).tickFormat((d)=> d + "%");

    // Add the y axis labels to the chart
    chart.append('g').attr('class', 'y-axis').attr('transform', 'translate(30,0)').call(yAxis)
      .selectAll('.tick').attr('transform', function() {return d3.select(this).attr('transform') + 'translate(41,-12)'})

    // Add horizontal tick marks
    chart.selectAll('line.horizontalGrid')
      .data(y.ticks(6).slice(1))
      .enter()
      .append('line')
      .attr('class', 'horizontalGrid')
      .attr('x1', margin['right'])
      .attr('x2', width + margin['right'] + margin['left'] + 10)
      .attr('y1', y)
      .attr('y2', y);

    // Draw vertical bars, add mouse-over logic
    chart.selectAll('rect')
      .data(objectKeys)
      .enter()
      .append('rect')
      .attr('class', (d,i) =>`bar-${i}`)
      .attr('width', BAR_WIDTH)
      .attr('height', ((d,i) => height * objectValues[i]/objectValues[0]))
      .attr('x', x)
      .attr('y', ((d, i) => {
        const val = height * (objectValues[0] - objectValues[i]) / (2 * objectValues[0]);
        barCorners.push([{"x":x(d) + GRAPH_OFFSET, "y": val }, {"x":x(d) + GRAPH_OFFSET, "y": height - val }]);
        barCorners.push([{"x":x(d) + GRAPH_OFFSET + BAR_WIDTH, "y": val },{"x":x(d) + GRAPH_OFFSET + BAR_WIDTH, "y": height - val }]);
        return val;
      }))
      .attr('transform', `translate(${GRAPH_OFFSET},0)`)
      .on('mouseover', addTooltip)
      .on('mouseout', removeTooltip);


    // Calculate polygon points based on column corners
    for(let i = 1; i < barCorners.length - 1; i = i + 2) {
      polygons.push([barCorners[i],barCorners[i + 1]]);
    }

    // Draws polygon to fill space below line in graph
    chart.selectAll('polygon')
      .data(polygons)
      .enter()
      .append('polygon')
      .attr('class', ((d,i) => 'polygon-' + i))
      .attr('points', (d => `${d[0][0].x},${d[0][0].y} ${d[0][1].x},${d[0][1].y} ${d[1][1].x},${d[1][1].y} ${d[1][0].x},${d[1][0].y}`));

    // Add labels to each rectangle
    chart.append('g').attr('class', 'x-axis')
      .call(xAxis)
      .selectAll('.tick')
      .data(objectKeys)
      .attr('transform', function() {return d3.select(this).attr('transform') + `translate(5,${2*height/5})`})
      .append('foreignObject')
      .attr('class', 'text-container')
      .attr('height', '100px')
      .attr('width', '80px')
      .style('color', (d,i) => objectValues[i]/objectValues[0] > 0.2 ? 'white' : '#98AFC7')
      .style('padding-top', (d,i) => objectValues[i]/objectValues[0] > 0.2 ? '0px' : 0.8 * height * objectValues[i]/objectValues[0] + 35 + 'px')
      .html((d, i) => `<p>${Math.round(100*objectValues[i]/objectValues[0])}% ${d}</p>`)
      .on('mouseover', addTooltip)
      .on('mouseout', removeTooltip);

    // Add horizontal tick marks
    chart.append('line')
      .attr('class', 'horizontalGrid')
      .attr('x1', margin['right'])
      .attr('x2', width + margin['right'] + margin['left'] + 10)
      .attr('y1', height)
      .attr('y2', height);

    // Draw X-Axis
    chart.append('line')
      .attr('x1', 0)
      .attr('x2', width + margin['right'] + 30)
      .attr('y1', 1.2 * height)
      .attr('y2', 1.2 * height)
      .attr('class', 'x-axis-line');
  }

}
