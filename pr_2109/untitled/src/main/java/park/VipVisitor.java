package park;

public class VipVisitor extends Visitor {
    private static final double VIP_DISCOUNT = 0.3;

    public VipVisitor(String name, int age, int height, double money) {
        super(name, age, height, money);
    }

    @Override
    public double getDiscount() {
        return VIP_DISCOUNT;
    }

    @Override
    public boolean isVip() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + " (VIP)";
    }
}