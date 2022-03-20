package org.jglrxavpok.jlsl;

import org.jglrxavpok.jlsl.fragments.CodeFragment;

@FunctionalInterface
public interface CodeFilter {
    public CodeFragment filter(CodeFragment fragment);
}
