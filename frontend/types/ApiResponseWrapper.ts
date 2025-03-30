export type ApiResponseWrapper<R> = {
	success: boolean
	code: number
	data: R | null
	message: string
	errors: string[]
}