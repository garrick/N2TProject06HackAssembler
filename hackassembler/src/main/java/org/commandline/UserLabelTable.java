package org.commandline;

import java.util.HashMap;

public class UserLabelTable {

    private HashMap<String, Integer> labelStore = new HashMap<>();
    public void storeLabel(String rawLabel, int position) {
        String cleanLabel = TokenUtils.sanitize(rawLabel);
        labelStore.put(cleanLabel, position);
    }

    public boolean hasLabel(String possibleLabel) {
        return labelStore.containsKey(TokenUtils.sanitize(possibleLabel));
    }

    public Integer getPositionForLabel(String possibleLabel) {return labelStore.get(TokenUtils.sanitize(possibleLabel));};
    public void dump() {
        labelStore.forEach((label,val) -> System.out.println(label+": "+val));
    }
}
