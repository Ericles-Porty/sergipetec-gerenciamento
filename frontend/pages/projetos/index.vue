<script setup lang="ts">
import type { ApiResponseWrapper } from '~/types/ApiResponseWrapper';
import type Projeto from '~/types/Projeto';


const endpoint = `/api/projetos`

const { data, status, error } = await useFetch(endpoint, {
	method: 'GET',
	headers: {
		'Content-Type': 'application/json'
	}
})

const response = data.value as ApiResponseWrapper<Projeto[]>

</script>

<template>
	<div>
		<h1>Projetos</h1>
		<div v-if="status === 'pending'">Carregando...</div>
		<div v-else-if="status === 'error'">Erro: {{ error }}</div>
		<div v-else-if="status === 'success'">
			<div v-for="projeto in response.data" :key="projeto.id">
				<h1>{{ `${projeto.id} - ${projeto.nome}` }}</h1>
				<h2>{{ projeto.descricao }}</h2>
				<p>Equipe: {{ projeto.equipe?.nome ?? 'Nenhuma' }}</p>
				<p>Status: {{ projeto.status }}</p>
			</div>
		</div>
	</div>
</template>
