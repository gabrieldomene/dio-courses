package digital.innovation.one.utils;

import digital.innovation.one.utils.internal.DivisorHelper;
import digital.innovation.one.utils.internal.MultiplierHelper;
import digital.innovation.one.utils.internal.SubtractHelper;
import digital.innovation.one.utils.internal.SumHelper;

public class Calculator {

    private final DivisorHelper divHelper;
    private final SumHelper sumHelper;
    private final MultiplierHelper multHelper;
    private final SubtractHelper subHelper;
    public Calculator() {

        this.divHelper = new DivisorHelper();
        this.subHelper = new SubtractHelper();
        this.multHelper = new MultiplierHelper();
        this.sumHelper = new SumHelper();

    }

    public int sum(int a, int b) {
        return this.sumHelper.execute(a, b);
    }

    public int sub(int a, int b) {
        return this.subHelper.execute(a, b);
    }

    public int div(int a, int b) {
        return this.divHelper.execute(a, b);
    }

    public int mul(int a, int b) {
        return this.multHelper.execute(a, b);
    }
}
