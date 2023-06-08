package org.eshop.exceptions;

public class PacksizeNotMatching extends Exception {
   public PacksizeNotMatching(int packsize) {
        super("Packzise is not matching:" + packsize);
    }
}
