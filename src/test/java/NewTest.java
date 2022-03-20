import org.jglrxavpok.jlsl.JLGL;
import org.jglrxavpok.jlsl.ObfuscationFilter;
import org.jglrxavpok.jlsl.glsl.FragmentShaderEnvironment;
import org.jglrxavpok.jlsl.glsl.sampler2D;
import org.jglrxavpok.jlsl.glsl.vec2;
import org.jglrxavpok.jlsl.glsl.vec4;
import org.junit.Test;

import static org.jglrxavpok.jlsl.glsl.GLSL.*;

public class NewTest {

    private static final JLGL jlgl = new JLGL(150);

    @Test
    public void main() {
        System.out.println(jlgl.generateGLSLShader(ExampleShader.class, new ObfuscationFilter()));
    }

    private static class ExampleShader extends FragmentShaderEnvironment {

        @Uniform sampler2D Sampler0;
        @Uniform vec4 ColorModulator;

        @In vec4 vertexColor;
        @In vec2 texCoord0;
        @In vec2 texCoord2;
        @In vec4 normal;

        @Out vec4 fragColor;

        @Override
        public void main() {
            vec4 color = texture(Sampler0, texCoord0).mul(vertexColor);
            if (color.a < 0.1) {
                return;
            }
            fragColor = color.mul(ColorModulator);
        }
    }

}
