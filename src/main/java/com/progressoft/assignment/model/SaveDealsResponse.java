package com.progressoft.assignment.model;

import java.util.List;
import java.util.UUID;

public record SaveDealsResponse(List<UUID> savedDealsIds, List<UUID> existingDealIds) {
}
