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
	</div>
</template>
