package com.progressoft.assignment.pojo;

import java.util.List;
import java.util.UUID;

public record SaveDealsResponse(List<UUID> savedDealsIds, List<UUID> existingDealIds) {
}
