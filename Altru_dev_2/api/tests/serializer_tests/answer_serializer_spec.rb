require 'serializers_spec_helper'

RSpec.describe AnswerSerializer, type: :serializer do

  describe 'answer serializer' do
    let!(:user){ create(:admin_user) }
    let(:question) { create(:question) }
    let(:answer) { create(:answer, question: question) }

    it 'matches answer_schema when user is passed' do
      expect(AnswerSerializer.new(answer, {params: {current_user: user}}).as_json['data']['attributes']).to match_json_schema("answer_schema")
    end

    it 'matches answer_schema when user is not passed' do
      expect(AnswerSerializer.new(answer, {params: {current_user: nil}}).as_json['data']['attributes']).to match_json_schema("answer_schema")
    end
  end

end
