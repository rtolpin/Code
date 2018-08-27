import { Component, OnInit, ElementRef, Input } from '@angular/core';
import * as d3 from 'd3';

@Component({
  selector: 'altru-gauge-chart',
  templateUrl: './gauge-chart.component.html',
  styleUrls: ['./gauge-chart.component.scss']
})
export class GaugeChartComponent implements OnInit {

  @Input()
  data: any;

  @Input()
  color: string;

  constructor(private element: ElementRef) { }

  public ngOnInit(): void {
      const element: ElementRef = this.element;
      const width: number = 150;
      const height: number = 150;
      const radius: number = Math.min(width, height)/2;

      const arc: any = d3.arc()
        .outerRadius(radius - 40)
        .innerRadius(radius - 30);

      const pie: any = d3.pie()
        .sort(null)
        .value((d,i)=> parseInt(d.toString()));

      const chart_container: any = d3.select(element.nativeElement)
        .append('div')
        .attr('class', 'gauge-chart-container ' + this.data['name'])
        .attr('max-width', width+20 + 'px')
        .attr('max-height', height+100 + 'px');

      const chart: any = chart_container.append('svg')
        .attr("width", width)
        .attr("height", height)
        .attr('class', 'gauge-chart')
        .append("g")
        .attr('class', 'chart-data')
        .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

      const g: any = chart.selectAll(".arc")
        .data(pie([this.data['value'], Math.ceil(100-this.data['value'])]))
        .enter().append("g")
        .attr("class", "arc")
        .append('path')
        .attr("d", arc)
        .style('fill', ((d)=>d['value'] == Math.floor(this.data['value']) ? this.color : "lightgrey"))
        .style('opacity', ((d)=>d['value'] == Math.floor(this.data['value']) ? '1' : '0.3'))
        // On mouseover, appends tooltip to svg chart - displaying data about each point in graph
        .on('mouseover', ((d, i, nodes) => {
          chart.append('foreignObject')
            .attr('class', 'tooltip-container')
            .append("xhtml:div")
            .attr('class', 'tooltip')
            .style('left', d['value'] == Math.floor(this.data['value']) ? '22px' : '-45px')
            .append('span')
            .text(`${d['value']}`)
            .attr('class', 'tooltip-text');
        }))
        // On mouseout, removes tooltip from the view
        .on('mouseout', ((d, i, nodes) => {
          d3.select(element.nativeElement).select('foreignObject').remove();
        }));

      chart.append('text')
        .text(this.valueFormatting(this.data['value']))
        .attr('transform', 'translate(0,5)');

      d3.select('.gauge-chart-container.' + this.data['name']).append('text')
        .text(this.data['name'])
        .style('top', height - 25 + 'px')
        .style('right', ((d,i, nodes)=> width/(d3.select(nodes[i]).text().length/3) + d3.select(nodes[i]).text().length * 3.5 + 'px'));
   }

   public valueFormatting(value): string{
     if (value > 75) {
       return 'HIGH';
     } else if (value > 50) {
       return 'MEDIUM';
     } else {
       return 'LOW';
     }
   }

}
