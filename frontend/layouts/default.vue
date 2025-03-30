<script setup lang="ts">
import AppHeader from '~/components/AppHeader.vue'
import AppFooter from '~/components/AppFooter.vue'
</script>

<template>
	<div class="h-screen w-screen flex flex-col justify-between items-center">
		<AppHeader />
		<main class="p-5 w-full h-full">
			<slot />
		</main>
		<AppFooter />
	</div>
</template>
