<script setup lang="ts">
const route = useRoute()
const runtimeConfig = useRuntimeConfig()
const { data, status, error } = await useFetch(() => runtimeConfig.public.apiBaseUrl + '/api/projetos/' + route.params.id)

type ApiResponse = {
	success: boolean
	code: number
	data: Project
	message: string
}

type Project = {
	id: number
	nome: string
	descricao: string
	dataInicio: string
	dataFim: string
	equipeResponsavel: string
	status: string
}

const response = data.value as ApiResponse;

</script>

<template>
	<div>
		<h1>Detalhes do Projeto</h1>
		<div v-if="status === 'pending'">Carregando...</div>
		<div v-else-if="status === 'error'">Erro: {{ error }}</div>
		<div v-else-if="status === 'success'">
			<h2>{{ response.data.id }}</h2>
			<p>{{ response.data.nome }}</p>
			<p>Equipe: {{ response.data.descricao }}</p>
			<p>Gerente: {{ response.data.status }}</p>
		</div>
	</div>
</template>
