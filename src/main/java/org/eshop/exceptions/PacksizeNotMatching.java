package org.eshop.exceptions;

public class PacksizeNotMatching extends Exception {
    int packsize;
   public PacksizeNotMatching(int packsize) {
        super("Packzise is not matching:" + packsize);
        this.packsize = packsize;
    }
    public int getPacksize() {
        return packsize;
    }
}
