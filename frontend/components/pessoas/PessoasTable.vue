<!-- components/PessoaTable.vue -->
<template>
	<div v-if="status === 'pending'">
		<div class="flex flex-col items-center justify-center gap-4">
			<UProgressBar class="w-full" />
			<p>Carregando...</p>
		</div>
	</div>
	<div v-else-if="status === 'error'">Erro: {{ error }}</div>
	<div v-else class="flex flex-col items-center justify-center gap-4">
		<UTable 
			sticky 
			:data="dataTable" 
			:columns="columns" 
			:sorting="sorting"
			class="flex-1 max-h-[512px] overflow-y-auto" />

	</div>
</template>

<script setup lang="ts">
import type Pessoa from '~/types/Pessoa'
import type { TableColumn } from '@nuxt/ui'
import { usePessoaApi } from '~/composables/usePessoaApi'

import type { Column, ColumnSort } from '@tanstack/vue-table'
const { pessoas, status, error } = usePessoaApi()

const UButton = resolveComponent('UButton')
const UDropdownMenu = resolveComponent('UDropdownMenu')

type PessoaTable = {
	id: number,
	nome: string,
	equipe: string
}

const dataTable = computed<PessoaTable[]>(
	() =>
		pessoas.value.map((pessoa: Pessoa) => {
			return {
				id: pessoa.id,
				nome: pessoa.nome,
				equipe: pessoa.equipe?.nome ?? 'Sem equipe'
			} as PessoaTable
		})
)

const columns: TableColumn<PessoaTable>[] = [
	{
		accessorKey: 'id',
		header: ({ column }) => getHeader(column, 'ID'),
		cell: ({ row }) => row.getValue('id')
	},
	{
		accessorKey: 'nome',
		header: ({ column }) => getHeader(column, 'Nome'),
		cell: ({ row }) => row.getValue('nome')
	},
	{
		accessorKey: 'equipe',
		header: ({ column }) => getHeader(column, 'Equipe'),
		cell: ({ row }) => row.getValue('equipe')
	},
	{
		accessorKey: 'delete',
		header: 'Apagar',
		cell: ({ row }) => {
			const pessoa = row.original as PessoaTable
			return h(UButton, {
				icon: 'i-lucide-trash-2',
				color: 'danger',
				variant: 'ghost',
				class: 'text-red-500',
				onClick: () => {
					if (confirm(`Deseja apagar ${pessoa.nome}?`)) {
						usePessoaApi().deletePessoa(pessoa.id).then(() => {
							alert('Pessoa apagada com sucesso!')
						}).catch((error) => {
							alert(`Erro ao apagar pessoa: ${error.message}`)
						})
					}
				}
			})
		}
	}
]

const sorting: ColumnSort[] = [
	{
		id: 'id',
		desc: false
	},
	{
		id: 'nome',
		desc: false
	},
	{
		id: 'equipe',
		desc: false
	}
]

function getHeader(column: Column<PessoaTable>, label: string) {
	const isSorted = column.getIsSorted()

	return h(
		UDropdownMenu,
		{
			content: {
				align: 'start'
			},
			'aria-label': 'Actions dropdown',
			items: [
				{
					label: 'Asc',
					type: 'checkbox',
					icon: 'i-lucide-arrow-up-narrow-wide',
					checked: isSorted === 'asc',
					onSelect: () => {
						if (isSorted === 'asc') {
							column.clearSorting()
						} else {
							column.toggleSorting(false)
						}
					}
				},
				{
					label: 'Desc',
					icon: 'i-lucide-arrow-down-wide-narrow',
					type: 'checkbox',
					checked: isSorted === 'desc',
					onSelect: () => {
						if (isSorted === 'desc') {
							column.clearSorting()
						} else {
							column.toggleSorting(true)
						}
					}
				}
			]
		},
		() =>
			h(UButton, {
				color: 'neutral',
				variant: 'ghost',
				label,
				icon: isSorted
					? isSorted === 'asc'
						? 'i-lucide-arrow-up-narrow-wide'
						: 'i-lucide-arrow-down-wide-narrow'
					: 'i-lucide-arrow-up-down',
				class: '-mx-2.5 data-[state=open]:bg-(--ui-bg-elevated)',
				'aria-label': `Sort by ${isSorted === 'asc' ? 'descending' : 'ascending'}`
			})
	)
}
</script>
