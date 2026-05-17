package com.inf1nlty.noanimalsafterbloodmoon.control;

import net.minecraft.NBTTagCompound;

public final class AnimalControlState {

    private static final String TAG = "NoAnimalsAfterBloodMoon";
    private static final String LEGACY_TAG = "AnimalSpawnTweaks";
    private static final String NO_ANIMAL_MODE = "NoAnimalMode";
    private static final String FIRST_BLOOD_MOON_DAY = "FirstBloodMoonDay";
    private static boolean noAnimalMode;
    private static int firstBloodMoonDay = -1;

    public static boolean isNoAnimalMode() {
        return noAnimalMode;
    }

    public static void setNoAnimalMode(boolean value) {
        noAnimalMode = value;
    }

    public static int getFirstBloodMoonDay() {
        return firstBloodMoonDay;
    }

    public static void setFirstBloodMoonDay(int day) {
        firstBloodMoonDay = day;
    }

    public static void readFromWorldInfo(NBTTagCompound nbt) {
        if (nbt == null) {
            reset();
            return;
        }

        String tagName = nbt.hasKey(TAG) ? TAG : (nbt.hasKey(LEGACY_TAG) ? LEGACY_TAG : null);
        if (tagName == null) {
            reset();
            return;
        }

        NBTTagCompound tag = nbt.getCompoundTag(tagName);
        noAnimalMode = tag.getBoolean(NO_ANIMAL_MODE);
        firstBloodMoonDay = tag.hasKey(FIRST_BLOOD_MOON_DAY) ? tag.getInteger(FIRST_BLOOD_MOON_DAY) : -1;
    }

    public static void writeToWorldInfo(NBTTagCompound nbt) {
        if (nbt == null) {
            return;
        }

        NBTTagCompound tag = new NBTTagCompound(TAG);
        tag.setBoolean(NO_ANIMAL_MODE, noAnimalMode);
        tag.setInteger(FIRST_BLOOD_MOON_DAY, firstBloodMoonDay);
        nbt.setCompoundTag(TAG, tag);
    }

    private static void reset() {
        noAnimalMode = false;
        firstBloodMoonDay = -1;
    }
}
