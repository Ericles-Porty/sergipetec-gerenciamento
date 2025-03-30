<script setup lang="ts">
const runtimeConfig = useRuntimeConfig()
</script>

<template>
	<div>
		<h1>Bem-vindo à Página Inicial</h1>
		<p>Conteúdo da página inicial aqui.</p>
		<div>
			<h3>Configurações de tempo de execução</h3>
			<p>API_URL: {{ runtimeConfig.public.apiBaseUrl }}</p>
		</div>
		<UModal>
			<UButton label="Open" color="neutral" variant="subtle" />
			<template #header>
				<h2 class="text-lg font-semibold">Título do Modal</h2>
			</template>
			<template #body>
				<div>
					<h2 class="text-lg font-semibold">Título do Modal</h2>
					<p>Conteúdo do modal aqui.</p>
				</div>
			</template>
		</UModal>
	</div>
</template>
