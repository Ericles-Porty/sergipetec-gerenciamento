import { useFetch } from '#app'
import type { ApiResponseWrapper } from '~/types/ApiResponseWrapper'
import type Pessoa from '~/types/Pessoa'


// Composable para lidar com a lógica das pessoas
export function usePessoaApi() {
	const endpoint = '/api/pessoas'

	// Chama a API para obter a lista de pessoas
	const { data, status, error, refresh } = useFetch<ApiResponseWrapper<Pessoa[]>>(endpoint, {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
		},
	})

	const pessoas = computed(() => data.value?.data ?? [])

	const refreshPessoas = async () => {
		await refresh()
	}

	// Função para adicionar uma pessoa
	const addPessoa = async (nome: string) => {
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

	return {
		pessoas,
		status,
		error,
		refreshPessoas,
		addPessoa,
	}
}
