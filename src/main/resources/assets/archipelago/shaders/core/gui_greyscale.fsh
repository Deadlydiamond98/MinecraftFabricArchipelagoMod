#version 150

uniform sampler2D Sampler0;
uniform vec4 ColorModulator;

in vec2 texCoord0;

out vec4 fragColor;

void main() {
    vec4 color = texture(Sampler0, texCoord0);

    if (color.a < 0.1) {
        discard;
    }

    float luminance = dot(color.rgb, vec3(0.299, 0.587, 0.114));
    vec4 grayColor = vec4(vec3(luminance), color.a);

    fragColor = grayColor * ColorModulator;
}
