export type SentimentType = 'Anger' | 'Disgust' | 'Fear' | 'Happiness' | 'Sadness' | 'Openness' | 'Caution' | 'Extraversion'
 | 'Agreeableness' | 'Emotional Range' | 'Analytical' | 'Confidence' | 'Tentative';

export class SentimentData {
  public name: SentimentType;
  public value: number;
  constructor(sentimentData: SentimentData) {
    this.name = sentimentData.name;
    this.value = sentimentData.value;
  }
}
