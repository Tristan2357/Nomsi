const colors = require("tailwindcss/colors");
module.exports = {
  content: ["./index.html", "./src/**/*.{vue,js,ts,jsx,tsx}"],
  darkMode: "media",
  theme: {
    extend: {},
    colors: {
      transparent: "transparent",
      current: "currentColor",
      black: colors.black,
      white: colors.white,
      neutral: colors.neutral,
      carrib: {
        lightest: "#22ffc4",
        light: "#00f1b1",
        DEFAULT: "#00C28E",
        dark: "#00a579",
        darkest: "#008863",
      },
      paradise: {
        lightest: "#ff5b94",
        light: "#ff387d",
        DEFAULT: "#ff1669",
        dark: "#ea0052",
        darkest: "#c10043",
      },
    },
  },
  plugins: [],
};
