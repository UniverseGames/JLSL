package org.jglrxavpok.jlsl;

import org.jglrxavpok.jlsl.fragments.CodeFragment;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class CodeEncoder {
    public JLSLContext context = null;

    public abstract void createSourceCode(List<CodeFragment> in, PrintWriter out);

    public void onRequestResult(ArrayList<CodeFragment> fragments) {

    }
}
