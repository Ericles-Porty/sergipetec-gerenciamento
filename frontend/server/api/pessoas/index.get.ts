import type { ApiResponseWrapper } from "~/types/ApiResponseWrapper"
import Equipe from "~/types/Equipe"
import type Pessoa from "~/types/Pessoa"

type PessoasResponse = {
	id: number
	nome: string
	equipe: EquipeResponse | null
}

type EquipeResponse = {
	id: number
	nome: string
}

export default defineEventHandler(async (event) => {
	const runtimeConfig = useRuntimeConfig(event)
	const endpoint = runtimeConfig.public.apiBaseUrl + '/api/pessoas'

	const response = await $fetch<ApiResponseWrapper<PessoasResponse[]>>(endpoint)

	if (response.code !== 200 || !response.data) {
		return response as ApiResponseWrapper<Pessoa[]>
	}

	const pessoas = mapPessoasResponseToPessoas(response.data as PessoasResponse[])

	return {
		success: response.success,
		code: response.code,
		data: pessoas,
		message: response.message,
		errors: response.errors
	} as ApiResponseWrapper<Pessoa[]>
})

function mapPessoasResponseToPessoas(responses: PessoasResponse[]): Pessoa[] {
	return responses.map((pessoa) => {
		return {
			id: pessoa.id,
			nome: pessoa.nome,
			equipe: pessoa.equipe ? new Equipe({ id: pessoa.equipe.id, nome: pessoa.equipe.nome }) : null
		}
	})
}