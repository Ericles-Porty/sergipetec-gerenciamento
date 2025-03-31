<script setup lang="ts">
import AdicionarPessoaModalButton from '~/components/pessoas/AdicionarPessoaModalButton.vue';
import PessoasTable from '~/components/pessoas/PessoasTable.vue';
import { usePessoaApi } from '~/composables/usePessoaApi'

const { refreshPessoas } = usePessoaApi()

const toast = useToast()

const newPersonAddedEventHandler = async (event: string) => {
	toast.add({
		title: `Sucesso ao adicionar ${event}`,
		duration: 5000,
		description: `${event} foi adicionada com sucesso`,
		close: true,
	})

	await refreshPessoas()
}
</script>

<template>
	<div class="h-full text-center flex flex-col items-center justify-start gap-6">
		<h1 class="text-6xl">Listagem de pessoas</h1>
		<AdicionarPessoaModalButton @new-person-added-event="newPersonAddedEventHandler" />
		<PessoasTable />
	</div>
</template>
