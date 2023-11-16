package christmas.domain.discount.enums;

public enum EventBadge {
    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000),
    ;

    private final String badge;
    private final int amount;

    EventBadge(String badge, int amount) {
        this.badge = badge;
        this.amount = amount;
    }

    public static String getBadge(int totalDiscountAmount) {
        if (isNoneBadge(totalDiscountAmount)) {
            return NONE.badge;
        }
        if (isStarBadge(totalDiscountAmount)) {
            return STAR.badge;
        }
        if (isTreeBadge(totalDiscountAmount)) {
            return TREE.badge;
        }
        return SANTA.badge;
    }

    private static boolean isNoneBadge(int totalDiscountAmount) {
        return totalDiscountAmount < STAR.amount;
    }

    private static boolean isStarBadge(int totalDiscountAmount) {
        return totalDiscountAmount < TREE.amount;
    }

    private static boolean isTreeBadge(int totalDiscountAmount) {
        return totalDiscountAmount < SANTA.amount;
    }
}
