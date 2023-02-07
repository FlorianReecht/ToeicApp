import { sveltekit } from '@sveltejs/kit/vite';

/** @type {import('vite').UserConfig} */
const config = {
	plugins: [sveltekit()],

	kit: {
		vite: {
			srr: {
				noExternal: ['@popperjs/core']
			}
		}
	}
};

export default config;
