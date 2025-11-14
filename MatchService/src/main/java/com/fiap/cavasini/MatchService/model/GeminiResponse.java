package com.fiap.cavasini.MatchService.model;

import lombok.Data;
import java.util.List;

@Data
public class GeminiResponse {

    private List<Candidate> candidates;
    private UsageMetadata usageMetadata;
    private String modelVersion;
    private String responseId;

    @Data
    public static class Candidate {
        private Content content;
        private String finishReason;
        private int index;
    }

    @Data
    public static class Content {
        private List<Part> parts;
        private String role;
    }

    @Data
    public static class Part {
        private String text;
    }

    @Data
    public static class UsageMetadata {
        private int promptTokenCount;
        private int candidatesTokenCount;
        private int totalTokenCount;
        private List<PromptTokensDetails> promptTokensDetails;
        private int thoughtsTokenCount;
    }

    @Data
    public static class PromptTokensDetails {
        private String modality;
        private int tokenCount;
    }
}
