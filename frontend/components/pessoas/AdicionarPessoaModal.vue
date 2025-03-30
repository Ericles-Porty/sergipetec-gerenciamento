<!-- components/PessoaModal.vue -->
<template>
	<UModal 
		v-model="modalOpenLocal"
		title="Adicionar Pessoa"
		aria-label="Adicionar Pessoa"
		description="Adicione uma pessoa ao sistema"
		aria-describedby="Adicione uma pessoa ao sistema"
		@close="modalOpenLocal = false"
		@closed="() => emit('update:isModalOpen', false)"
		>
		<UButton 
			icon="lucide:plus" 
			label="Adicionar Pessoa" 
			color="neutral" 
			variant="subtle"
			@click="modalOpenLocal = true" />
		<template #header>
			<h2 class="text-lg font-semibold">Adicionar Pessoa</h2>
		</template>
		<template #body>
			<UForm :schema="schemaAddPessoa" :state="stateAddPessoa" @submit="onSubmitAddPessoa">
				<div class="flex flex-row gap-4 items-end">
					<UFormField label="Nome da pessoa" name="nome" class="w-full">
						<div class="flex flex-row justify-between items-center">
							<UInput 
								v-model="stateAddPessoaLocal.nome" 
								placeholder="Nome da pessoa" required
								class="w-6/10" />
							<UButton 
								icon="lucide:save" 
								label="Adicionar" 
								color="primary" 
								variant="solid" 
								type="submit"
								loading-auto 
								class="h-8 w-3/10" />
						</div>
					</UFormField>
				</div>
			</UForm>
		</template>
	</UModal>
</template>

<script setup lang="ts">
import type { FormSubmitEvent } from '@nuxt/ui'
import type { SchemaAddPessoa } from '~/schemas/SchemaAddPessoa'

const props = defineProps<{
	isModalOpen: boolean
	schemaAddPessoa: object
	stateAddPessoa: { nome: string }
}>()

// Estados locais
const modalOpenLocal = ref(props.isModalOpen)
const stateAddPessoaLocal = reactive(props.stateAddPessoa)

// Eventos
const emit = defineEmits<{
	(e: 'update:isModalOpen', value: boolean): void
	(e: 'submit', event: FormSubmitEvent<SchemaAddPessoa>): void
}>()

const onSubmitAddPessoa = (event: FormSubmitEvent<SchemaAddPessoa>) => {
	modalOpenLocal.value = false
	emit('update:isModalOpen', false)
	emit('submit', event)
}

// Observadores
watch(() => props.isModalOpen, (newVal) => {
	modalOpenLocal.value = newVal
})

watch(() => props.stateAddPessoa, (newState) => {
	stateAddPessoaLocal.nome = newState.nome
})

watch(modalOpenLocal, (newVal) => {
	console.log('modalOpenLocal', newVal)
	emit('update:isModalOpen', newVal)
})

</script>