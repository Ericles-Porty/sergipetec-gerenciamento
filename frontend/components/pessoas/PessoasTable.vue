<!-- components/PessoaTable.vue -->
<template>
	<div v-if="status === 'pending'">Carregando...</div>
	<div v-else-if="status === 'error'">Erro: {{ error }}</div>
	<div v-else class="flex flex-col items-center justify-center gap-4">
		<table class="table-auto">
			<thead>
				<tr>
					<th class="px-4 py-2">Id</th>
					<th class="px-4 py-2">Nome</th>
					<th class="px-4 py-2">Equipe</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="pessoa in pessoas" :key="pessoa.id">
					<td class="border px-4 py-2">{{ pessoa.id }}</td>
					<td class="border px-4 py-2">{{ pessoa.nome }}</td>
					<td class="border px-4 py-2">{{ pessoa.equipe?.nome ?? 'Sem equipe' }}</td>
				</tr>
			</tbody>
		</table>
	</div>
</template>

<script setup lang="ts">
import type { AsyncDataRequestStatus } from '#app';
import type Pessoa from '~/types/Pessoa'

defineProps<{
	pessoas: Pessoa[],
	status: AsyncDataRequestStatus,
	error: string | null
}>()
</script>
