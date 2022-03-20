package org.jglrxavpok.jlsl;

import org.jglrxavpok.jlsl.fragments.CodeFragment;

import java.io.PrintWriter;
import java.util.ArrayList;

public class JLSLContext {

    public static JLSLContext currentInstance;
    private final CodeDecoder decoder;
    private final CodeEncoder encoder;
    private Object object;

    public JLSLContext(CodeDecoder decoder, CodeEncoder encoder) {
        JLSLContext.currentInstance = this;
        this.decoder = decoder;
        this.decoder.context = this;
        this.encoder = encoder;
        this.encoder.context = this;
    }

	public void requestAnalysisForEncoder(Object data) {
		encoder.onRequestResult(execute(data));
    }

    public void execute(Object data, PrintWriter out) {
		encoder.createSourceCode(execute(data), out);
    }

	private ArrayList<CodeFragment> execute(Object data) {
		this.object = data;
		ArrayList<CodeFragment> fragments = new ArrayList<>();
		decoder.handleClass(data, fragments);
		ArrayList<CodeFragment> finalFragments = new ArrayList<>();
		for (CodeFragment frag : fragments) {
			if (frag != null) finalFragments.add(frag);
		}
		return finalFragments;
	}

	public Object getCurrentObject() {
        return object;
    }
}
