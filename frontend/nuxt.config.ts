// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
	compatibilityDate: '2024-11-01',
	devtools: { enabled: true },

	runtimeConfig: {
		public: {
			apiBaseUrl: 'http://localhost:8080'
		},
	},

	modules: [
		'@nuxt/content',
		'@nuxt/eslint',
		'@nuxt/fonts',
		'@nuxt/icon',
		'@nuxt/image',
		'@nuxt/scripts',
		'@nuxt/test-utils',
		'@nuxt/ui',
	],

	css: ['~/assets/main.css'],

	app: {
		pageTransition: { name: 'page', mode: 'out-in' }
	},

	// routeRules: {
	// 	'/': { prerender: true },
	// },

})