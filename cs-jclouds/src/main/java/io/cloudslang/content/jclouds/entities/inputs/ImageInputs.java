package io.cloudslang.content.jclouds.entities.inputs;

import io.cloudslang.content.jclouds.entities.aws.ImageState;
import io.cloudslang.content.jclouds.entities.aws.ImageType;
import io.cloudslang.content.jclouds.entities.constants.Constants;
import io.cloudslang.content.jclouds.utils.InputsUtil;

/**
 * Created by Mihai Tusa.
 * 5/10/2016.
 */
public class ImageInputs {
    private CustomInputs customInputs;

    private String imageName;
    private String imageDescription;
    private String imageIdsString;
    private String ownersString;
    private String userIdsString;
    private String userGroupsString;
    private String description;
    private String type;
    private String isPublic;
    private String manifestLocation;
    private String state;

    private boolean imageNoReboot;

    private ImageInputs(Builder builder) {
        this.customInputs = builder.customInputs;

        this.imageName = builder.imageName;
        this.imageDescription = builder.imageDescription;
        this.imageIdsString = builder.imageIdsString;
        this.ownersString = builder.ownersString;
        this.userIdsString = builder.userIdsString;
        this.userGroupsString = builder.userGroupsString;
        this.description = builder.description;
        this.type = builder.type;
        this.isPublic = builder.isPublic;
        this.manifestLocation = builder.manifestLocation;
        this.state = builder.state;

        this.imageNoReboot = builder.imageNoReboot;
    }

    public CustomInputs getCustomInputs() {
        return customInputs;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public String getImageIdsString() {
        return imageIdsString;
    }

    public String getOwnersString() {
        return ownersString;
    }

    public String getUserIdsString() {
        return userIdsString;
    }

    public String getUserGroupsString() {
        return userGroupsString;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public String getManifestLocation() {
        return manifestLocation;
    }

    public String getState() {
        return state;
    }

    public boolean isImageNoReboot() {
        return imageNoReboot;
    }

    public static class Builder {
        private CustomInputs customInputs;

        private String imageName;
        private String imageDescription;
        private String imageIdsString;
        private String ownersString;
        private String userIdsString;
        private String userGroupsString;
        private String description;
        private String type;
        private String isPublic;
        private String manifestLocation;
        private String state;

        private boolean imageNoReboot;

        public ImageInputs build() {
            return new ImageInputs(this);
        }

        public Builder withCustomInputs(CustomInputs inputs) {
            customInputs = inputs;
            return this;
        }

        public Builder withImageName(String inputValue) {
            imageName = InputsUtil.getDefaultStringInput(inputValue, Constants.Miscellaneous.NOT_RELEVANT);
            return this;
        }

        public Builder withImageIdsString(String inputValue) {
            imageIdsString = InputsUtil.getDefaultStringInput(inputValue, Constants.Miscellaneous.EMPTY);
            return this;
        }

        public Builder withOwnersString(String inputValue) {
            ownersString = InputsUtil.getDefaultStringInput(inputValue, Constants.Miscellaneous.EMPTY);
            return this;
        }

        public Builder withImageDescription(String inputValue) {
            imageDescription = InputsUtil.getDefaultStringInput(inputValue, Constants.Miscellaneous.EMPTY);
            return this;
        }

        public Builder withUserIdsString(String inputValue) {
            userIdsString = inputValue;
            return this;
        }

        public Builder withUserGroupsString(String inputValue) {
            userGroupsString = inputValue;
            return this;
        }

        public Builder withDescription(String inputValue) {
            description = inputValue;
            return this;
        }

        public Builder withType(String inputValue) throws Exception {
            type = ImageType.getValue(inputValue);
            return this;
        }

        public Builder withIsPublic(String inputValue) {
            isPublic = InputsUtil.getRelevantBooleanString(inputValue);
            return this;
        }

        public Builder withManifestLocation(String inputValue) {
            manifestLocation = inputValue;
            return this;
        }

        public Builder withState(String inputValue) throws Exception {
            state = ImageState.getValue(inputValue);
            return this;
        }

        public Builder withImageNoReboot(String inputValue) {
            imageNoReboot = InputsUtil.getEnforcedBooleanCondition(inputValue, true);
            return this;
        }
    }
}
