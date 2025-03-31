<!-- components/pessoas/AdicionarPessoaModalButton.vue -->
<template>
	<UModal 
		v-model:open="isModalOpen"
		title="Adicionar Pessoa" 
		description="Adicionar uma nova pessoa ao sistema"
		>

		<UButton 
			icon="lucide:plus" 
			label="Adicionar Pessoa" 
			color="neutral" 
			variant="subtle"
			@click="isModalOpen = true" />

		<template #body>
			<UForm 
				:schema="schemaAddPessoa" 
				:state="stateAddPessoa" 
				@submit="onSubmitAddPessoa"
				>
				<div class="flex flex-row gap-4 items-end">
					<UFormField label="Nome da pessoa" name="nome" class="w-full">
						<div class="flex flex-row justify-between items-center">
							<UInput 
								v-model="stateAddPessoa.nome" 
								placeholder="Nome da pessoa" 
								required
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
import { useToast } from '#imports';
import type { FormSubmitEvent } from '@nuxt/ui'
import type * as z from 'zod'
import { schemaAddPessoa } from '~/schemas/SchemaAddPessoa';

const { addPessoa } = usePessoaApi()
const toast = useToast()


const stateAddPessoa = reactive({ nome: '' })
const isModalOpen = ref(false)

const emit = defineEmits<{
	(e: 'newPersonAddedEvent', event: string): void
}>()

const onSubmitAddPessoa = async (event: FormSubmitEvent<z.output<typeof schemaAddPessoa>>) => {
	isModalOpen.value = false
	stateAddPessoa.nome = ''
	const response = await addPessoa(event.data.nome)

	if (response.success === false) {
		toast.add({
			title: 'Erro ao adicionar pessoa',
			description: response.errors?.join(', ') ?? 'Erro desconhecido',
			duration: 5000,
			color: 'error',
		})

		return
	}

	emit('newPersonAddedEvent', event.data.nome)
}

</script>