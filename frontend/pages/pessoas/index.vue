<script setup lang="ts">
import type { FormSubmitEvent } from '@nuxt/ui';
import type * as z from 'zod'
import AdicionarPessoaModal from '~/components/pessoas/AdicionarPessoaModal.vue';
import PessoasTable from '~/components/pessoas/PessoasTable.vue';
import { usePessoaApi } from '~/composables/usePessoaApi'
import { schemaAddPessoa } from '~/schemas/SchemaAddPessoa';

// Composable para lidar com a API de pessoas
const { pessoas, status, error, refreshPessoas, addPessoa } = usePessoaApi()

// Estados reativos
const stateAddPessoa = reactive({ nome: '' })
const isModalOpen = ref(false)

type SchemaAddPessoa = z.output<typeof schemaAddPessoa>

// Toast para exibir mensagens de erro ou sucesso
const toast = useToast()

// Função para adicionar uma pessoa ao submeter o formulário
const onSubmitAddPessoa = async (event: FormSubmitEvent<SchemaAddPessoa>) => {
	const nome = event.data.nome

	const response = await addPessoa(nome)

	if (response.success === false) {
		toast.add({
			title: response.message ?? 'Erro desconhecido',
			duration: 5000,
			description: response.errors?.join(' & ') ?? 'Erro desconhecido',
			close: true,
			color: 'error',
		})
		return
	}

	toast.add({
		title: `Sucesso ao adicionar ${nome}`,
		duration: 5000,
		description: `${nome} foi adicionada com sucesso`,
		close: true,
	})

	isModalOpen.value = false
	stateAddPessoa.nome = ''

	await refreshPessoas()
}
</script>

<template>
	<div class="h-full text-center flex flex-col items-center justify-start gap-12">
		<h1 class="text-6xl">Listagem de pessoas</h1>
		<PessoasTable 
			:pessoas="pessoas" 
			:status="status" 
			:error="error?.message ?? null" />

		<AdicionarPessoaModal 
			v-model:is-modal-open="isModalOpen" 
			:schema-add-pessoa="schemaAddPessoa"
			:state-add-pessoa="stateAddPessoa" 
			@submit="onSubmitAddPessoa"
			@update:is-modal-open="isModalOpen = $event" />
	</div>
</template>
