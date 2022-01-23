const colors = require('tailwindcss/colors')

module.exports = {
  purge: ['./public/**/*.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  darkMode: 'media',
  theme: {
    extend: {},
    colors: {
      transparent: 'transparent',
      current: 'currentColor',
      black: colors.black,
      white: colors.white,
      carrib: {
        lightest: '#22ffc4',
        light: '#00f1b1',
        DEFAULT: '#00C28E',
        dark: '#00a579',
        darkest: '#008863',
      },
      paradise: {
        lightest: '#ff5b94',
        light: '#ff387d',
        DEFAULT: '#ff1669',
        dark: '#ea0052',
        darkest: '#c10043'
      },
      rose: colors.rose,
      pink: colors.pink,
      fuchsia: colors.fuchsia,
      purple: colors.purple,
      violet: colors.violet,
      indigo: colors.indigo,
      blue: colors.blue,
      sky: colors.sky,
      cyan: colors.cyan,
      teal: colors.teal,
      emerald: colors.emerald,
      green: colors.green,
      lime: colors.lime,
      yellow: colors.yellow,
      amber: colors.amber,
      orange: colors.orange,
      red: colors.red,
      warmGray: colors.warmGray,
      trueGray: colors.trueGray,
      gray: colors.gray,
      coolGray: colors.coolGray,
      blueGray: colors.blueGray,
    }
  },
  variants: {
    extend: {},
  },
  plugins: [
    require('@tailwindcss/forms')
  ],
}
