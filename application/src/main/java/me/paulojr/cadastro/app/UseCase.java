package me.paulojr.cadastro.app;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN anIn);

}
