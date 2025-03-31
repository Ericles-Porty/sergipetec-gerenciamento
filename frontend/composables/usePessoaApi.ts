import { useFetch } from '#app'
import type { ApiResponseWrapper } from '~/types/ApiResponseWrapper'
import type Pessoa from '~/types/Pessoa'

export function usePessoaApi() {
	const endpoint = '/api/pessoas'

	const { data, status, error, refresh } = useFetch<ApiResponseWrapper<Pessoa[]>>(endpoint, {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
		},
	})

	const pessoas: Ref<Pessoa[]> = computed(() => data.value?.data ?? [])

	const refreshPessoas = async () => {
		await refresh()
	}

	async function addPessoa(nome: string): Promise<ApiResponseWrapper<null>> {
		{
			const response = await $fetch<ApiResponseWrapper<null>>(endpoint, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
				},
				ignoreResponseError: true,
				body: { nome },
			})

			return response
		}
	}

	async function deletePessoa(id: number): Promise<ApiResponseWrapper<null>> {
		{
			const response = await $fetch<ApiResponseWrapper<null>>(`${endpoint}/${id}`, {
				method: 'DELETE',
				headers: {
					'Content-Type': 'application/json',
				},
				ignoreResponseError: true,
			})

			return response
		}
	}

	async function updatePessoa(id: number, nome: string): Promise<ApiResponseWrapper<null>> {

		const response = await $fetch<ApiResponseWrapper<null>>(`${endpoint}/${id}`, {
			method: 'PUT',
			headers: {
				'Content-Type': 'application/json',
			},
			ignoreResponseError: true,
			body: {
				id,
				nome,
			},
		})

		return response

	}

	return {
		pessoas,
		status,
		error,
		refreshPessoas,
		addPessoa,
		deletePessoa,
		updatePessoa,
	}
}
