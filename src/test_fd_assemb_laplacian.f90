program test_fd_assemb_laplacian
  use iso_fortran_env, only: real64
  implicit none
  interface
    subroutine fd_assemb_laplacian(ff, a, b, c, sigma, v, vr, vc)
      use iso_fortran_env, only: real64
      implicit none
      real(real64), intent(in) :: ff
      real(real64), intent(in) :: a(:), b(:), c(:)
      real(real64), intent(in) :: sigma(:, :)
      complex(real64), allocatable, intent(out) :: v(:)
      integer, allocatable, intent(out) :: vr(:), vc(:)
    end subroutine fd_assemb_laplacian
  end interface
  real(real64) :: a(3), b(3), c(3)
  real(real64) :: sigma(27,6)
  complex(real64), allocatable :: v(:)
  integer, allocatable :: vr(:), vc(:)
  integer :: base, ind

  a = 1d0
  b = 1d0
  c = 1d0
  sigma = 0d0
  sigma(:,1) = 1d0
  sigma(:,2) = 1d0
  sigma(:,3) = 1d0

  call fd_assemb_laplacian(0d0, a, b, c, sigma, v, vr, vc)

  ! Check one interior Ex entry (k=2,j=2,i=2)
  ind = 1*3*(3+1) + 1*3 + 2   ! matches subroutine formula
  base = (ind - 1) * 7

  if (abs(v(base + 1) + 1d0) > 1d-12) stop 1
  if (abs(v(base + 2) + 1d0) > 1d-12) stop 2
  if (abs(v(base + 3) + 1d0) > 1d-12) stop 3
  if (abs(v(base + 4) + 1d0) > 1d-12) stop 4
  if (abs(v(base + 5) + 1d0) > 1d-12) stop 5
  if (abs(v(base + 6) + 1d0) > 1d-12) stop 6
  if (abs(v(base + 7) - 6d0) > 1d-12) stop 7

  print *, 'fd_assemb_laplacian test passed.'
end program test_fd_assemb_laplacian
