import { Component, Input, OnInit } from '@angular/core';
import { Sentiment } from 'altru-core';

@Component({
  selector: 'altru-sentiment',
  templateUrl: './sentiment.component.html',
  styleUrls: ['./sentiment.component.scss']
})
export class SentimentComponent implements OnInit {

  @Input()
  public sentiment: Sentiment;

  public positiveSentiments: {[sentimentLabel: string]: number};
  public negativeSentiments: {[sentimentLabel: string]: number};
  public positiveNotPerceptible: string[] = [];
  public negativeNotPerceptible: string[] = [];
  public sentimentGradeArray: string[] = ['A+', 'A+', 'A', 'A', 'A-', 'A-', 'B+', 'B+', 'B', 'B', 'B-', 'B-'];
  public score: number = 0;

  constructor() { }

  public ngOnInit(): void {
    this.splitSentimentsByCategory();
    this.score = this.calcScore();
  }

  public get scoreColor(): string {
    if (this.score <= 3) {
      return '#45CAD6';
    } else if (this.score <= 7) {
      return '#FFED4C';
    } else {
      return '#C64650';
    }
  }

  private splitSentimentsByCategory(): void {
    this.positiveSentiments = {
      emotion_joy: this.sentiment.emotion_joy,
      language_analytical: this.sentiment.language_analytical,
      language_confident: this.sentiment.language_confident,
      social_openness: this.sentiment.social_openness,
      social_conscientiousness: this.sentiment.social_conscientiousness,
      social_agreeableness: this.sentiment.social_agreeableness
    };
    for (const key of Object.keys(this.positiveSentiments)) {
      const value: number = this.positiveSentiments[key];
      if (value === 0) {
        this.positiveNotPerceptible.push(this.formatLabel(key));
      }
    }

    this.negativeSentiments = {
      emotion_anger: this.sentiment.emotion_anger,
      emotion_disgust: this.sentiment.emotion_disgust,
      emotion_fear: this.sentiment.emotion_fear,
      emotion_sadness: this.sentiment.emotion_sadness
    };
    for (const key of Object.keys(this.negativeSentiments)) {
      const value: number = this.negativeSentiments[key];
      if (value === 0) {
        this.negativeNotPerceptible.push(this.formatLabel(key));
      }
    }
  }

  private calcScore(): number {
    let partial: number = 0;

    for (const key of Object.keys(this.negativeSentiments)) {
      const negativeSentimentValue: number = this.negativeSentiments[key];

      if (negativeSentimentValue >= .75) {
        partial += 2;
      } else if (negativeSentimentValue >= .5) {
        partial += 1;
      }
    }

    for (const key of Object.keys(this.positiveSentiments)) {
      const positiveSentimentValue: number = this.positiveSentiments[key];

      if (positiveSentimentValue < .5) {
        partial += 2;
      } else if (positiveSentimentValue < .75) {
        partial += 1;
      }
    }

    return partial;
  }

  private formatLabel(label: string): string {
    switch (label) {
      case 'emotion_joy': return 'Happiness';
      case 'language_analytical': return 'Analytical';
      case 'language_confident': return 'Confident';
      case 'social_openness': return 'Openness';
      case 'social_conscientiousness': return 'Conscientiousness';
      case 'social_agreeableness': return 'Agreeableness';
      case 'emotion_anger': return 'Anger';
      case 'emotion_disgust': return 'Disgust';
      case 'emotion_fear': return 'Fear';
      case 'emotion_sadness': return 'Sadness';
    }
  }
}
