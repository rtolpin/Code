import { Component, OnChanges, ElementRef, Input } from '@angular/core';
import * as d3 from 'd3';

@Component({
  selector: 'altru-line-chart',
  templateUrl: './line-chart.component.html',
  styleUrls: ['./line-chart.component.scss']
})
export class LineChartComponent implements OnChanges {

  @Input()
  data: object[];

  constructor(private element: ElementRef) { }

  // Creates D3 line graph with connected points representing video views
  ngOnChanges(): void {
  if(this.data){
    const element: ElementRef = this.element;
    const margin: object = {top: 20, right: 20, bottom: 40, left: 20};
    const width: number = 1280 - margin['left'] - margin['right'];
    const height: number = 300 - margin['top'] - margin['bottom'];
    const dataValues: string[] = this.data.map(d => d[Object.keys(d)[0]]);
    const dataKeys: string[] = this.data.map(d => Object.keys(d)[0]);

    // Clear any existing svgs so we start with a clean slate
    d3.select(this.element.nativeElement).select('svg').remove();

    // Instantiate the d3 chart
    const chart = d3.select(element.nativeElement)
      .append('svg')
      .attr('width', width + margin['left'] + margin['right'])
      .attr('height', height + margin['top'] + margin['bottom'])
      .attr('class', 'line-chart')
      .append('g')
      .attr('class', 'chart-data')
      .attr('transform', 'translate(-20,24)')


    // Define x and y scalers
    const x:any = d3.scaleBand().domain(dataKeys).rangeRound([0, width]).padding(.05);
    const y:any = d3.scaleLinear().domain([0, parseInt(d3.max(dataValues))]).range([height, 0]);

    // Define x and y axis
    const yAxis:any = d3.axisLeft(y).tickSize(0).tickPadding(20).ticks(5).tickFormat(d3.format('2d'));
    const xAxis:any = d3.axisBottom(x).tickSize(0).tickPadding(10).ticks(1);

    // Add the x and y axis to the chart
    const addYAxis:any = chart.append('g').attr('class', 'y-axis').attr("transform", "translate(45,-15)").call(yAxis);
    const addXAxis:any = chart.append('g').attr('class', 'x-axis').attr('transform', `translate(20,${height-2})`).call(xAxis).attr('dy', '.3em').attr('dx', '1.5em');

    // Space out y-axis ticks
    addYAxis
      .selectAll('.tick')
      .attr('transform', ((d, i, nodes) => d3.select(nodes[i]).attr('transform') + 'translate(20,0)'));

    // Space out x-axis ticks
    addXAxis
      .selectAll('.tick')
      .attr('transform', ((d, i, nodes) => d3.select(nodes[i]).attr('transform') + 'translate(20,0)'));

    // Format text for x-axis ticks
    addXAxis
      .selectAll('text')
      .attr('transform', 'translate(20,3)');

    // Instantiates array for polygon values below line graph
    const poly = [{'x': dataKeys[0], 'y': '0'}];

    // Draws lines from point to point in graph, connecting all points
    this.data.forEach((val, i)=>{
     if(i != this.data.length-1){
      chart.append('line')
        .attr('x1', () => x(dataKeys[i]))
        .attr('x2', () => x(dataKeys[i+1]))
        .attr('y1', () => height)
        .attr('y2', () => height)
        .attr('class', 'connect-points')
        .attr('transform', addXAxis.selectAll('.tick').attr('transform'))
        .transition()
        .duration(800)
        .style('opacity', 1)
        .attr('y1', () => y(dataValues[i]) - 2)
        .attr('y2', () => y(dataValues[i+1]) - 2)
       // Pushes to polygon values array to set points for polygon
       poly.push({'x': dataKeys[i], 'y': dataValues[i]}, {'x': dataKeys[i+1], 'y': dataValues[i+1]});
     }
   });
   poly.push({'x': dataKeys[dataKeys.length-1], 'y': '0'});

    // Draws horizontal grid lines across chart to display y values
    chart.selectAll('line.horizontalGrid')
      .data(y.ticks(5).slice(1))
      .enter()
      .append('line')
      .attr('class', 'horizontalGrid')
      .attr('x1', margin['right'])
      .attr('x2', width + margin['right'] + 2)
      .attr('y1', ((d)=> y(d) - 5))
      .attr('y2', ((d)=> y(d) - 5));

    // Declare gradient for below line
    const gradient = chart
      .append("svg:defs")
      .append("svg:linearGradient")
      .attr("id", "gradient")
      .attr("x1", "100%")
      .attr("y1", "100%")
      .attr("x2", "100%")
      .attr("y2", "20%")
      .attr("spreadMethod", "pad");

    // Define the gradient colors
    gradient.append("svg:stop")
      .attr("offset", "0%")
      .attr("stop-color", "#ffffff");

    gradient.append("svg:stop")
      .attr("offset", "100%")
      .attr("stop-color", "#98AFC7");

    // Draws polygon to fill space below line in graph
    chart.selectAll('polygon')
      .data([poly])
      .enter()
      .append('polygon')
      .attr('transform', addXAxis.selectAll('.tick').attr('transform'))
      .attr('points', (d => d.map(d => [x(d.x), height].join(',')).join(' ')))
      .attr('class', 'polygon')
      .style('fill', 'url(#gradient) #ffffff')
      .style('opacity', 0)
      .transition()
      .duration(800)
      .style('opacity', 0.5)
      .attr('points', (d => d.map(d => [x(d.x), y(d.y) - 1].join(',')).join(' ')));

   // Draws this.data point circles on graph, and handles tooltip mouse over event
   chart.selectAll('circle')
    .data(Object.keys(this.data))
    .enter()
    .append('circle')
    .attr('class', 'dot')
    .attr('r', 4.2)
    .attr('cx', ((d)=> x(dataKeys[d])))
    .attr('cy', height)
    .attr('transform', addXAxis.selectAll('.tick').attr('transform'))
    .style('fill', '#98AFC7')
    // On mouseover, appends tooltip to svg chart - displaying this.data about each point in graph
    .on('mouseover', ((d, i, nodes) => {
      d3.select(nodes[i])
        .transition()
        .duration(300)
        .attr('r', 6)
        .style('fill', '#003366');
      d3.select(element.nativeElement)
        .select('svg').append('foreignObject')
        .attr('class', 'tooltip-container')
        .attr('width', '100%')
        .attr('height', '100%')
        .append("xhtml:div")
        .attr('class', 'tooltip')
        .style('top', parseInt(dataValues[d]) >= parseInt(d3.max(dataValues)) - parseInt(d3.max(dataValues))/5 ? (y(dataValues[d]) + 32).toString() + 'px' : (y(dataValues[d]) - 9).toString() + 'px')
        .style('left', (x(dataKeys[d]) - 10).toString() + 'px')
        .append('span')
        .attr('class', 'tooltip-text')
        .text(`${dataKeys[d]}: ${dataValues[d]} Views`);
      d3.select(element.nativeElement)
        .select('foreignObject')
        .style('opacity', 0)
        .transition()
        .duration(300)
        .style('opacity', 1);
    }))
    // On mouseout, removes tooltip from the view
    .on('mouseout', ((d, i, nodes) => {
      d3.select(nodes[i])
        .transition()
        .duration(300)
        .style('fill', '#98AFC7')
        .attr('r', 4.2);
      d3.select(element.nativeElement).select('foreignObject').remove();
    }))
    .style('opacity', 0)
    .transition()
    .duration(800)
    .style('opacity', 1)
    .attr('cy', ((d)=> y(dataValues[d]) - 2));

    // Hide x axis ticks if too many x axis ticks present
    if(dataKeys.length > 15) {
      addXAxis.selectAll('.tick').style('visibility', 'hidden');
      addXAxis.selectAll('.tick').filter((d, i) => i % Math.round(dataKeys.length/12) == 0).style('visibility', 'visible');
      addXAxis.selectAll('.tick').attr('transform', ((d, i, nodes)=> d3.select(nodes[i]).attr('transform') + 'translate(' + (margin['right']*(Math.ceil(dataKeys.length/100)) + 2) + ',0)'));
    }
  }
 }

}
