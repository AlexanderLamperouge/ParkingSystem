subroutine fd_assemb_laplacian(ff, a, b, c, sigma, v, vr, vc)
  ! Assemble 7-point finite-difference matrix for (Laplacian + i*omega*mu*sigma)
  use iso_fortran_env, only: real64
  implicit none
  real(real64), intent(in) :: ff
  real(real64), intent(in) :: a(:), b(:), c(:)
  real(real64), intent(in) :: sigma(:, :)
  complex(real64), allocatable, intent(out) :: v(:)
  integer, allocatable, intent(out) :: vr(:), vc(:)

  real(real64), parameter :: pi = 3.14159265358979323846_real64
  real(real64), parameter :: mu = 4e-7_real64 * pi
  real(real64) :: w, sigmas, ve
  complex(real64) :: coef
  integer :: nx, ny, nz
  integer :: xnl, ynl, znl, nl
  integer :: nnz, ind
  integer :: i, j, k, ie, base

  ! Frequency term
  w = 2d0 * pi * ff
  coef = cmplx(0d0, 1d0, kind(0d0)) * w * mu

  nx = size(a); ny = size(b); nz = size(c)
  xnl = nx * (ny + 1) * (nz + 1)
  ynl = ny * (nz + 1) * (nx + 1)
  znl = nz * (nx + 1) * (ny + 1)
  nl = xnl + ynl + znl
  nnz = nl * 7

  allocate(v(nnz), vr(nnz), vc(nnz))
  v = 0d0
  vr = 0
  vc = 0

  !-----------------------------------------------------------------------
  ! Ex edges: include x, y, z second derivatives
  !-----------------------------------------------------------------------
  do k = 2, nz
    do j = 2, ny
      do i = 2, nx - 1
        ie = (k - 2) * nx * ny + (j - 2) * nx + i
        ve = a(i) * (b(j - 1) + b(j)) * (c(k - 1) + c(k)) / 4d0

        sigmas = (sigma(ie, 1) * b(j - 1) * c(k - 1) + sigma(ie + nx, 1) * b(j) * c(k - 1) + &
                  sigma(ie + nx * ny, 1) * b(j - 1) * c(k) + sigma(ie + nx * ny + nx, 1) * b(j) * c(k)) / &
                 ((b(j - 1) + b(j)) * (c(k - 1) + c(k)))

        ind = int(k - 1, 8) * nx * (ny + 1) + int(j - 1, 8) * nx + int(i, 8)
        base = (ind - 1) * 7
        vr(base + 1:base + 7) = ind

        ! x- neighbor
        vc(base + 1) = ind - 1
        v(base + 1) = -2d0 / (a(i - 1) * (a(i - 1) + a(i)))

        ! x+ neighbor
        vc(base + 2) = ind + 1
        v(base + 2) = -2d0 / (a(i) * (a(i - 1) + a(i)))

        ! y- neighbor
        vc(base + 3) = ind - nx
        v(base + 3) = -2d0 / (b(j - 1) * (b(j - 1) + b(j)))

        ! y+ neighbor
        vc(base + 4) = ind + nx
        v(base + 4) = -2d0 / (b(j) * (b(j - 1) + b(j)))

        ! z- neighbor
        vc(base + 5) = ind - nx * (ny + 1)
        v(base + 5) = -2d0 / (c(k - 1) * (c(k - 1) + c(k)))

        ! z+ neighbor
        vc(base + 6) = ind + nx * (ny + 1)
        v(base + 6) = -2d0 / (c(k) * (c(k - 1) + c(k)))

        ! central term
        vc(base + 7) = ind
        v(base + 7) = -(v(base + 1) + v(base + 2) + v(base + 3) + v(base + 4) + v(base + 5) + v(base + 6)) - coef * sigmas

        v(base + 1:base + 7) = v(base + 1:base + 7) * ve
      end do
    end do
  end do

  !-----------------------------------------------------------------------
  ! Ey edges: include x, y, z second derivatives
  !-----------------------------------------------------------------------
  do k = 2, nz - 1
    do j = 2, ny - 1
      do i = 2, nx
        ie = (k - 2) * nx * ny + (j - 1) * nx + i - 1
        ve = b(j) * (a(i - 1) + a(i)) * (c(k - 1) + c(k)) / 4d0

        sigmas = (sigma(ie, 2) * a(i - 1) * c(k - 1) + sigma(ie + 1, 2) * a(i) * c(k - 1) + &
                  sigma(ie + nx * ny, 2) * a(i - 1) * c(k) + sigma(ie + nx * ny + 1, 2) * a(i) * c(k)) / &
                 ((a(i - 1) + a(i)) * (c(k - 1) + c(k)))

        ind = int(xnl, 8) + int(k - 1, 8) * (nx + 1) * ny + int(j - 1, 8) * (nx + 1) + int(i, 8)
        base = (ind - 1) * 7
        vr(base + 1:base + 7) = ind

        ! x- neighbor
        vc(base + 1) = ind - 1
        v(base + 1) = -2d0 / (a(i - 1) * (a(i - 1) + a(i)))

        ! x+ neighbor
        vc(base + 2) = ind + 1
        v(base + 2) = -2d0 / (a(i) * (a(i - 1) + a(i)))

        ! y- neighbor
        vc(base + 3) = ind - (nx + 1)
        v(base + 3) = -2d0 / (b(j - 1) * (b(j - 1) + b(j)))

        ! y+ neighbor
        vc(base + 4) = ind + (nx + 1)
        v(base + 4) = -2d0 / (b(j) * (b(j - 1) + b(j)))

        ! z- neighbor
        vc(base + 5) = ind - (nx + 1) * ny
        v(base + 5) = -2d0 / (c(k - 1) * (c(k - 1) + c(k)))

        ! z+ neighbor
        vc(base + 6) = ind + (nx + 1) * ny
        v(base + 6) = -2d0 / (c(k) * (c(k - 1) + c(k)))

        ! central term
        vc(base + 7) = ind
        v(base + 7) = -(v(base + 1) + v(base + 2) + v(base + 3) + v(base + 4) + v(base + 5) + v(base + 6)) - coef * sigmas

        v(base + 1:base + 7) = v(base + 1:base + 7) * ve
      end do
    end do
  end do

  !-----------------------------------------------------------------------
  ! Ez edges: include x, y, z second derivatives
  !-----------------------------------------------------------------------
  do k = 2, nz - 1
    do j = 2, ny
      do i = 2, nx - 1
        ie = (k - 1) * nx * ny + (j - 2) * nx + i - 1
        ve = c(k) * (b(j - 1) + b(j)) * (a(i - 1) + a(i)) / 4d0

        sigmas = (sigma(ie, 3) * b(j - 1) * a(i - 1) + sigma(ie + nx, 3) * b(j) * a(i - 1) + &
                  sigma(ie + 1, 3) * b(j - 1) * a(i) + sigma(ie + nx + 1, 3) * b(j) * a(i)) / &
                 ((b(j - 1) + b(j)) * (a(i - 1) + a(i)))

        ind = int(xnl, 8) + int(ynl, 8) + int(k - 1, 8) * (nx + 1) * (ny + 1) + int(j - 1, 8) * (nx + 1) + int(i, 8)
        base = (ind - 1) * 7
        vr(base + 1:base + 7) = ind

        ! x- neighbor
        vc(base + 1) = ind - 1
        v(base + 1) = -2d0 / (a(i - 1) * (a(i - 1) + a(i)))

        ! x+ neighbor
        vc(base + 2) = ind + 1
        v(base + 2) = -2d0 / (a(i) * (a(i - 1) + a(i)))

        ! y- neighbor
        vc(base + 3) = ind - (nx + 1)
        v(base + 3) = -2d0 / (b(j - 1) * (b(j - 1) + b(j)))

        ! y+ neighbor
        vc(base + 4) = ind + (nx + 1)
        v(base + 4) = -2d0 / (b(j) * (b(j - 1) + b(j)))

        ! z- neighbor
        vc(base + 5) = ind - (nx + 1) * (ny + 1)
        v(base + 5) = -2d0 / (c(k - 1) * (c(k - 1) + c(k)))

        ! z+ neighbor
        vc(base + 6) = ind + (nx + 1) * (ny + 1)
        v(base + 6) = -2d0 / (c(k) * (c(k - 1) + c(k)))

        ! central term
        vc(base + 7) = ind
        v(base + 7) = -(v(base + 1) + v(base + 2) + v(base + 3) + v(base + 4) + v(base + 5) + v(base + 6)) - coef * sigmas

        v(base + 1:base + 7) = v(base + 1:base + 7) * ve
      end do
    end do
  end do
end subroutine fd_assemb_laplacian
