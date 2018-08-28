import { Component, Input, OnInit } from '@angular/core';
import { Sentiment } from 'altru-core';

@Component({
  selector: 'altru-sentiment-chart',
  templateUrl: './sentiment-chart.component.html',
  styleUrls: ['./sentiment-chart.component.scss']
})

export class SentimentChartComponent implements OnInit {

  @Input()
  public data: Sentiment;

  @Input()
  public sentimentsType: 'positives' | 'negatives';

  public color: string;
  public results: {name: string, value: number}[] = [];

  constructor() { }

  public ngOnInit(): void {
    this.results = this.getSentimentValues();
    this.color = this.sentimentsType === 'positives' ? '#45CAD6' : '#C64650';
  }

  public getRoundedValue(value: number): number {
    return Math.round(value);
  }

  private getSentimentValues(): {name: string, value: number}[] {
    const normalizedData: Sentiment = Object.assign(this.data);
    for (const key of Object.keys(normalizedData)) {
      normalizedData[key] = normalizedData[key] * 100;
    }

    return !normalizedData ? [] : [
      ...normalizedData.emotion_anger ? [{ name: 'Anger', value: normalizedData.emotion_anger }] : [],
      ...normalizedData.emotion_fear ? [{ name: 'Fear', value: normalizedData.emotion_fear }] : [],
      ...normalizedData.emotion_joy ? [{ name: 'Happiness', value: normalizedData.emotion_joy }] : [],
      ...normalizedData.emotion_sadness ? [{ name: 'Sadness', value: normalizedData.emotion_sadness }] : [],
      ...normalizedData.language_analytical ? [{ name: 'Analytical', value: normalizedData.language_analytical }] : [],
      ...normalizedData.language_confident ? [{ name: 'Confident', value: normalizedData.language_confident }] : [],
      ...normalizedData.language_tentative ? [{ name: 'Tentative', value: normalizedData.language_tentative }] : [],
      ...normalizedData.social_openness ? [{ name: 'Openness', value: normalizedData.social_openness }] : [],
      // ...normalizedData.social_conscientiousness ? [{ name: 'Conscientiousness', value: normalizedData.social_conscientiousness }] : [],
      ...normalizedData.social_agreeableness ? [{ name: 'Agreeableness', value: normalizedData.social_agreeableness }] : [],
      ...normalizedData.social_emotional_range ? [{ name: 'Emotional Range', value: normalizedData.social_emotional_range }] : [],
    ].sort((a, b) => b.value - a.value);
  }
}
