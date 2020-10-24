package org.commandline.hackassembler.table;

import org.commandline.hackassembler.util.DebugFlag;
import org.commandline.hackassembler.util.TokenUtils;

import java.util.HashMap;

public class UserLabelTable {

    private final HashMap<String, Integer> labelStore = new HashMap<>();

    public void storeLabel(String rawLabel, int position) {
        String cleanLabel = TokenUtils.sanitizeLabel(rawLabel);
        labelStore.put(cleanLabel, position);
    }

    public boolean hasLabel(String possibleLabel) {
        return labelStore.containsKey(TokenUtils.sanitizeLabel(possibleLabel));
    }

    public Integer getPositionForLabel(String possibleLabel) {
        return labelStore.get(TokenUtils.sanitizeLabel(possibleLabel));
    }

    public void dump() {
        System.out.println(DebugFlag.leftMarginPadding() + "====LABELS===");
        labelStore.forEach((label, val) -> System.out.println(DebugFlag.leftMarginPadding() + label + ": " + val));
    }
}
