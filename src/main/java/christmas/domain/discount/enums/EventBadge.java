package christmas.domain.discount.enums;

public enum EventBadge {
    NONE("없음"),
    STAR("별"),
    TREE("트리"),
    SANTA("산타"),
    ;

    private static final int MIN_STAR_DISCOUNT_AMOUNT = 5000;
    private static final int MIN_TREE_DISCOUNT_AMOUNT = 10000;
    private static final int MIN_SANTA_DISCOUNT_AMOUNT = 20000;
    private final String badge;

    EventBadge(String badge) {
        this.badge = badge;
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
        return totalDiscountAmount < MIN_STAR_DISCOUNT_AMOUNT;
    }

    private static boolean isStarBadge(int totalDiscountAmount) {
        return totalDiscountAmount < MIN_TREE_DISCOUNT_AMOUNT;
    }

    private static boolean isTreeBadge(int totalDiscountAmount) {
        return totalDiscountAmount < MIN_SANTA_DISCOUNT_AMOUNT;
    }
}
