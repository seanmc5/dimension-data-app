package com.gmail.seanmc560.dimensiondataapp.transformers;

public interface Transformer<In, Out> {

  Out transform(In in);
}
