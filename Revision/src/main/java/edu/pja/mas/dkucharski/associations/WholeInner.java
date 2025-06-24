package edu.pja.mas.dkucharski.associations;

import java.util.ArrayList;
import java.util.List;

public class WholeInner {
    private String wholeName;
    private List<Part> parts = new ArrayList<>();

    public WholeInner(String wholeName) {
        setName(wholeName);
    }

    public Part createPart(String partName) {
        Part part = new Part(partName);
        parts.add(part);

        return part;
    }

    public void setName(String wholeName) {
        if (wholeName == null || wholeName.trim().isEmpty()) {
            throw new IllegalArgumentException("Whole name cannot be null or empty");
        }
        this.wholeName = wholeName;
    }

    public class Part {
        private String partName;

        private Part(String partName) {
            setPartName(partName);
        }

        public WholeInner getWhole() {
            return WholeInner.this;
        }

        public void setPartName(String partName) {
            if (partName == null || partName.trim().isEmpty()) {
                throw new IllegalArgumentException("Part name cannot be null or empty");
            }
            this.partName = partName;
        }
    }
}
